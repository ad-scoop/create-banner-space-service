FROM java:8-jdk-alpine

COPY build/distributions/website-service.zip /usr/lib/adscoop/website-service.zip

RUN cd /usr/lib/adscoop/ && unzip website-service.zip

RUN rm /usr/lib/adscoop/website-service.zip

EXPOSE 8183

CMD ["sh", "/usr/lib/adscoop/create-banner-space-service/bin/website-service"]
