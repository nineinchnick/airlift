package io.airlift.tracing;

import io.airlift.configuration.Config;
import io.airlift.configuration.validation.FileExists;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OpenTelemetryConfig
{
    private String endpoint = "http://localhost:4317";
    private String resourceAttributesFile;

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

    @FileExists
    public String getResourceAttributesFile()
    {
        return resourceAttributesFile;
    }

    @Config("tracing.resource-attributes-file")
    public OpenTelemetryConfig setResourceAttributesFile(String resourceAttributesFile)
    {
        this.resourceAttributesFile = resourceAttributesFile;
        return this;
    }
}
