package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private final String PORT;
    private final String MEMORY_LIMIT;
    private final String CF_INSTANCE_INDEX;
    private final String CF_INSTANCE_ADDR;

    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}") String memoryLimit,
                         @Value("${cf.instance.index:NOT SET}") String cfInstanceIndex,
                         @Value("${cf.instance.address:NOT SET}") String cfInstanceAddr) {
        this.PORT = port;
        this.MEMORY_LIMIT = memoryLimit;
        this.CF_INSTANCE_INDEX = cfInstanceIndex;
        this.CF_INSTANCE_ADDR = cfInstanceAddr;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map envVariables = new HashMap<String, String>();
        envVariables.put("PORT", this.PORT);
        envVariables.put("MEMORY_LIMIT", this.MEMORY_LIMIT);
        envVariables.put("CF_INSTANCE_INDEX", this.CF_INSTANCE_INDEX);
        envVariables.put("CF_INSTANCE_ADDR", this.CF_INSTANCE_ADDR);

        return envVariables;
    }
}
