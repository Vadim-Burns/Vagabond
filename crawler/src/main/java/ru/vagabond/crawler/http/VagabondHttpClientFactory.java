package ru.vagabond.crawler.http;

import lombok.RequiredArgsConstructor;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.springframework.stereotype.Component;
import ru.vagabond.crawler.properties.HttpClientProperties;

@Component
@RequiredArgsConstructor
public class VagabondHttpClientFactory {

    private final HttpClientProperties httpClientProperties;

    /**
     * Потом либо переделать в контекст, либо что-то еще...
     */
    public VagabondHttpClient getClient() {
        return new VagabondHttpClientImpl(
                new DefaultAsyncHttpClient(
                        Dsl.config()
                                .setDisableHttpsEndpointIdentificationAlgorithm(true)
                                .setConnectTimeout(10000)
                                .setMaxConnections(100)
                                .setIoThreadsCount(httpClientProperties.getIoThreadCount())
                                .setFollowRedirect(false)
                                .build()
                )
        );
    }
}
