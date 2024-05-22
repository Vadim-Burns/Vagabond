package ru.vagabond.crawler.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
public class VagabondHttpClientImpl implements VagabondHttpClient {

    private static final VagabondHttpResponse EXCEPTION_RESPONSE = new VagabondHttpResponse(500, "");

    private final AsyncHttpClient httpClient;

    @Override
    public int headUrl(String url) throws InterruptedException {
        try {
            log.debug("HEAD requesting {}", url);
            int statusCode = httpClient.prepareHead(url).execute().get().getStatusCode();
            log.debug("HEAD requesting {} => {}", url, statusCode);
            return statusCode;
        } catch (InterruptedException e) {
            log.info("Interrupting http request");
            throw e;
        } catch (ExecutionException e) {
            log.info("Exception on HEAD requesting {}", url);
            log.debug("Exception on HEAD requesting {}", url, e);
            return 500;
        }
    }

    @Override
    public VagabondHttpResponse getUrl(String url) throws InterruptedException {
        try {
            log.debug("GET requesting {}", url);
            Response response = httpClient.prepareGet(url).execute().get();
            log.debug("GET REQUESTING {} => {}", url, response.getStatusCode());
            return new VagabondHttpResponse(response.getStatusCode(), response.getResponseBody());
        } catch (InterruptedException e) {
            log.info("Interrupting http request");
            throw e;
        } catch (ExecutionException e) {
            log.info("Exception on GET requesting {}", url);
            log.debug("Exception on GET requesting {}", url, e);
            return EXCEPTION_RESPONSE;
        }
    }
}
