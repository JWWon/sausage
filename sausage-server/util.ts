import moment = require('moment');

export function getTodayString() {
  return moment().format('YYYY-MM-DD');
}

export function isToday(date: string) {
  return moment('2017-08-17').isSame(date, 'day');
}
