import * as winston from 'winston';

const timestampFunc = () => (new Date()).toLocaleTimeString();

/**
 * Formatter used for winston logger. Enables logging of stack when handling exceptions.
 * @param {boolean} colorize
 * @param {Object}  options
 * @returns {string}
 */
const customFormatter = (colorize: boolean, options: any) => {
  const time = timestampFunc() + ' ';
  const logLevel = colorize ? winston.config.colorize(options.level, options.level.toUpperCase()) : options.level.toUpperCase();
  const message = options.message ? ' ' + options.message : ' ';
  const stack = options.meta && options.meta.stack ? options.meta.stack : '';

  return time + logLevel + message + stack;
};

const logger = new winston.Logger({
  transports: [
    new winston.transports.Console({
      timestamp       : timestampFunc(),
      handleExceptions: true,
      formatter       : customFormatter.bind(null, true),
      level           : 'debug'
    })
  ]
});

export default logger;
