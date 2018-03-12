FROM gcr.io/google_appengine/nodejs

RUN install_node v8.0.0

# Copy application code.
COPY . /app/

# Install dependencies.
RUN rm -rf node_modules
RUN rm -rf dist
RUN npm install --unsafe-perm || \
  ((if [ -f npm-debug.log  ]; then \
        cat npm-debug.log; \
            fi) && false)
CMD npm start
