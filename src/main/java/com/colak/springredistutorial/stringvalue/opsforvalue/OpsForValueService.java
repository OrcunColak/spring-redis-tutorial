package com.colak.springredistutorial.stringvalue.opsforvalue;

import java.util.concurrent.TimeUnit;

public interface OpsForValueService {

    Boolean delete(String key);

    Boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit);
}
