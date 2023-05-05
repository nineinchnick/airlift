package io.airlift.tracing;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static io.airlift.configuration.testing.ConfigAssertions.assertFullMapping;
import static io.airlift.configuration.testing.ConfigAssertions.assertRecordedDefaults;
import static io.airlift.configuration.testing.ConfigAssertions.recordDefaults;

public class TestOpenTelemetryConfig
{
    @Test
    public void testDefaults()
    {
        assertRecordedDefaults(recordDefaults(OpenTelemetryConfig.class)
                .setEndpoint("http://localhost:4317")
                .setResourceAttributesFile(null));
    }

    @Test
    public void testExplicitPropertyMappings()
    {
        File attributesFile = Files.newTemporaryFile();
        Map<String, String> properties = ImmutableMap.<String, String>builder()
                .put("tracing.exporter.endpoint", "http://example.com:1234")
                .put("tracing.resource-attributes-file", attributesFile.getAbsolutePath())
                .buildOrThrow();

        OpenTelemetryConfig expected = new OpenTelemetryConfig()
                .setEndpoint("http://example.com:1234")
                .setResourceAttributesFile(attributesFile.getAbsolutePath());

        assertFullMapping(properties, expected);
    }
}
