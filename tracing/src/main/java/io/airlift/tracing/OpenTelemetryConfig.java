package io.airlift.tracing;

import com.google.common.base.Splitter;
import io.airlift.configuration.Config;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.Map;

import static java.util.Objects.requireNonNull;

public class OpenTelemetryConfig
{
    private String endpoint = "http://localhost:4317";
    private Map<String, String> resourceAttributes = Map.of();

    @NotNull
    @Pattern(regexp = "^(http|https)://.*$", message = "must start with http:// or https://")
    public String getEndpoint()
    {
        return endpoint;
    }

    @Config("tracing.exporter.endpoint")
    public OpenTelemetryConfig setEndpoint(String endpoint)
    {
        this.endpoint = endpoint;
        return this;
    }

    @NotNull
    public Map<String, String> getResourceAttributes()
    {
        return resourceAttributes;
    }

    @Config("tracing.resource.attributes")
    public OpenTelemetryConfig setResourceAttributes(String resourceAttributes)
    {
        this.resourceAttributes = Splitter
                .on(',')
                .trimResults()
                .omitEmptyStrings()
                .withKeyValueSeparator("=")
                .split(requireNonNull(resourceAttributes, "resourceAttributes is null"));
        return this;
    }
}
