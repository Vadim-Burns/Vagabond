package ru.vagabond.crawler.http;

public interface VagabondHttpClient {

    /**
     * Make HEAD http request to url
     *
     * @return 500 if exception throws
     */
    int headUrl(String url) throws InterruptedException;

    /**
     * Make GET http request to url
     *
     * @return 500 if exception throws
     */
    VagabondHttpResponse getUrl(String url) throws InterruptedException;

}
