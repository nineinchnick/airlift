package io.airlift.tracing;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

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
                .setResourceAttributes(""));
    }

    @Test
    public void testExplicitPropertyMappings()
    {
        Map<String, String> properties = ImmutableMap.<String, String>builder()
                .put("tracing.exporter.endpoint", "http://example.com:1234")
                .put("tracing.resource.attributes", "a=b,c=d")
                .buildOrThrow();

        OpenTelemetryConfig expected = new OpenTelemetryConfig()
                .setEndpoint("http://example.com:1234")
                .setResourceAttributes("a=b,c=d");

        assertFullMapping(properties, expected);
    }
}
