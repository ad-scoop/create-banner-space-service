FROM java:8-jdk-alpine

COPY build/distributions/website-service.zip /usr/lib/adscoop/website-service.zip

RUN cd /usr/lib/adscoop/ && unzip create-banner-space-service.zip

RUN rm /usr/lib/adscoop/create-banner-space-service.zip

EXPOSE 8183

CMD ["sh", "/usr/lib/adscoop/create-banner-space-service/bin/website-service"]
