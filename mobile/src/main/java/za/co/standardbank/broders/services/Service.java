package za.co.standardbank.broders.services;

import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by a159937 on 2014/12/05.
 */
public class Service {

    RestTemplate restTemplate;

    public Service() {
        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new GsonHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
    }

    private HttpEntity<?> getRequestEntity() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAcceptEncoding(ContentCodingType.GZIP);
        //requestHeaders.add("token", Cache.getInstance().getToken());
        return new HttpEntity<Object>(requestHeaders);
    }

    private HttpEntity<?> getRequestEntity(Object t) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAcceptEncoding(ContentCodingType.GZIP);
        //requestHeaders.add("token", Cache.getInstance().getToken());
        return new HttpEntity<Object>(t, requestHeaders);
    }

    public <T> ResponseEntity<T> POST(String path, Object request, Class<T> responseObjCls) {
        return restTemplate.exchange(path, HttpMethod.POST, getRequestEntity(request), responseObjCls);
    }

    public <T> ResponseEntity<T> GET(String path, Class<T> responseObjCls) {
        return restTemplate.exchange(path, HttpMethod.GET, getRequestEntity(), responseObjCls);
    }

    public <T> ResponseEntity<T> PUT(String path, Object request, Class<T> responseObjCls) {
        return restTemplate.exchange(path, HttpMethod.PUT, getRequestEntity(request), responseObjCls);
    }

}
