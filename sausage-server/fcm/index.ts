import logger from '../logger';
const FCM = require('fcm-push');

const serverKey = process.env.FIREBASE_SERVER_KEY;
export const fcm = new FCM(serverKey);

export interface PushMessage {
  to: string,
  collapse_key?: string,
  data: {
    title: string,
    url: string
  }
}

export function createPushMessage(post: { title: string, url: string }): PushMessage {
  return {
    to  : '/topics/news',
    data: {
      title: post.title,
      url  : post.url
    }
  };
}

export const push = {
  send: function send(message: PushMessage) {
    return fcm.send(message)
      .then(function (response) {
        logger.info('Successfully sent with response: ', response);
      })
      .catch(function (err) {
        logger.error('Something has gone wrong!');
        logger.error(err);
      });
  }
};
