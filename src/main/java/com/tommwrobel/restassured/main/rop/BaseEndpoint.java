package com.tommwrobel.restassured.main.rop;

import io.restassured.response.Response;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Type;

public abstract class BaseEndpoint<E, M> {

    protected Response response;

    protected abstract Type getModelType();

    public abstract E sendRequest();

    protected abstract int getSuccessStatusCode();

    public M getResponseModel() {
        return response.as(getModelType());
    }

    public E assertRequestSuccess() {
        return assertStatusCode(getSuccessStatusCode());
    }

    public E assertStatusCode(int statusCode) {
        assertThat(response.getStatusCode()).as("Status code").isEqualTo(statusCode);
        return (E) this;
    }
}
