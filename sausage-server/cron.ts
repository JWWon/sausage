import * as phantom from 'phantom';
import * as cheerio from 'cheerio';
import { model } from './fdb/index';
import logger from './logger';
import * as request from 'request';
import { createPushMessage, push } from './fcm/index';
import md5 = require('md5');
import moment = require('moment');

async function getRecalls() {
  const recalls: {
    [hash: string]: {
      company: string,
      name: string,
      imageUrl: string,
      reason: string,
      date,
    }
  } = {};

  const recallUrl = 'http://www.foodsafetykorea.go.kr/portal/fooddanger/suspension.do?menu_no=2713&menu_grp=MENU_NEW02';

  const instance = await phantom.create();
  const page = await instance.createPage();

  await page.open(recallUrl);
  const content = await page.property('content');
  await instance.exit();

  const $: any = cheerio.load(content);

  const recalls$ = $('#listFrame');
  const recallList: any = [];

  recalls$.children().each(function () {
    recallList.push($(this));
  });

  for (let i = 0; i < recallList.length; i++) {
    const recallItem = recallList[i];

    const name = recallItem.find('li.issue').text().trim();
    const company = recallItem.find('li.product').text().trim();
    const imageStyle = recallItem.find('li.img-222x175').attr('style');
    const date = recallItem.find('li.datetxt').eq(1).text()
      .slice(7).trim().replace(/\./gi, '-');

    const urlStart = imageStyle.indexOf('(') + 1;
    const urlEnd = imageStyle.indexOf(')');

    const imageUrl = imageStyle.slice(urlStart, urlEnd);

    const imgOnclick = recallItem.find('a.recall').attr('onclick');

    const recallNumStart = imgOnclick.indexOf('(') + 2;
    const recallNumEnd = imgOnclick.indexOf(')') - 1;
    const recallNum = +imgOnclick.slice(recallNumStart, recallNumEnd);

    const url = `http://www.foodsafetykorea.go.kr/popup/suspensionDetail.do?search_keyword=${recallNum}`;

    const $$: any = await new Promise((resolve, reject) => {
      return request(url, function (err, response, body) {
        if (err) reject(err);

        resolve(cheerio.load(body, {decodeEntities: false}));
      });
    });
    const reason = $$('.issue-info > dl dd').eq(2).text();

    logger.info(`${name}:${company}:${reason}:${imageUrl}:${date}`);

    const recall = {
      company,
      name,
      imageUrl,
      reason,
      date
    };

    recalls[md5(JSON.stringify(recall))] = recall;
  }

  return recalls;
}

async function getPosts() {
  const posts: { [hash: string]: { title: string, url: string, date: string } } = {};

  const postUrl = 'http://foodsafetykorea.go.kr/hazard/foodInjryInfo/foodInjryInfo.do?type=A';

  const instance = await phantom.create();
  const page = await instance.createPage();

  await page.open(postUrl);
  const content = await page.property('content');
  await instance.exit();

  const $ = cheerio.load(content);

  const posts$ = $('#search_list');
  const postList: any = [];

  posts$.children().each(function () {
    postList.push($(this));
  });

  for (let i = 0; i < postList.length; i++) {
    const postItem = postList[i];
    const title = postItem.find('.con_list > span > a').text().trim();
    const postOnclick = postItem.find('.con_list > span > a').attr('onclick');

    const postNumStart = postOnclick.indexOf('(') + 2;
    const postNumEnd = postOnclick.indexOf(')') - 1;
    const postNum = +postOnclick.slice(postNumStart, postNumEnd);

    const url = `http://foodsafetykorea.go.kr/hazard/foodInjryInfo/searchFoodInjryInfoDetail.do?start_idx=1&show_cnt=5&lifecyc_meta_cd=ALL&kwrd_chrctr=&injry_info_nm=&food_injry_info_seq=${postNum}&type=A&search_keyword=`;

    const date = postItem.find('.date_list').text().trim().replace(/\./gi, '-');

    logger.info(`${postNum}:${date}:${title}`);

    const post = {title, url, date};

    posts[md5(JSON.stringify(post))] = post;
  }

  return posts;
}


export const cronjob = async function () {
  const posts = await getPosts();
  const recalls = await getRecalls();

  const latestPost = Object.keys(posts).map(key => posts[key])
    .sort((a, b) => moment(a.date).isBefore(moment(b.date)) ? 1 : -1)[0];

  if (latestPost) {
    push.send(createPushMessage(latestPost))
      .then(() => logger.info('successfully pushed to fcm'))
      .catch(err => logger.error(err));
  }

  model.uploadData('/posts', posts)
    .then(() => logger.info('successfully uploaded posts to fdb'))
    .catch(err => logger.error(err));

  model.uploadData('/recalls', recalls)
    .then(() => logger.info('successfully uploaded recalls to fdb'))
    .catch(err => logger.error(err));
};
