import logger from './logger';
import { cronjob } from './cron';
const CronJob = require('cron').CronJob;

new CronJob('*/15 * * * * *', cronjob, function () {
  logger.info('cronjob done');
}, true, 'Asia/Seoul');
