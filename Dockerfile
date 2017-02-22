FROM java:8-jdk-alpine

COPY build/distributions/create-banner-space-service.zip /usr/lib/adscoop/create-banner-space-service.zip

RUN cd /usr/lib/adscoop/ && unzip create-banner-space-service.zip

RUN rm /usr/lib/adscoop/create-banner-space-service.zip

RUN /usr/lib/adscoop/create-banner-space-service/bin/create-banner-space-service