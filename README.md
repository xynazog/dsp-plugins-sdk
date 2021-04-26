# DSP Plugins SDK

An SDK for writing custom functions for the Splunk Data Stream Processor. Please refer to the [documentation](https://docs.splunk.com/Documentation/DSP/latest/User/PluginSDK) for the most up to date information.

## Build Requirements

- Java 8

## Getting Started with Examples

- Clone this repository
- Update `gradle.properties`. Make sure the DSP API endpoint is accessible

#### The `gradle.properties` file neeeds the follwoing properties:

1. `SCLOUD_TOKEN_FILE` - This is the location of the file that has the access token that you get after running `scloud login` on the DSP instance. No other metadata in the file.
2. `PLUGIN_UPLOAD_SERVICE_PROTOCOL` - This can be `http` or `https`.
3. `PLUGIN_UPLOAD_SERVICE_HOST` - This is the host of the DSP instance.
4. `PLUGIN_UPLOAD_SERVICE_PORT` - DSP port.
5. `PLUGIN_UPLOAD_SERVICE_ENDPOINT` - Upload service endpoinit for plugins. The latest one is `streams/v3beta1/plugins`.
6. `PLUGIN_UPLOAD_INSECURE` - This can be `true` or `false`.

Run project setup using gradle with the help of the following commands:

#### Build dsp-plugin-examples module with example functions

`$ ./gradlew dsp-plugin-examples:build`

#### Register the plugin with DSP

`$ ./gradlew registerPlugin -PSDK_PLUGIN_NAME=dsp-sdk-examples -PSDK_PLUGIN_DESC="Example SDK Functions"`

#### List all the plugins

`$ ./gradlew getPlugins`

#### Upload the jar built in the dsp-plugin-examples module to DSP

`$ ./gradlew uploadPlugin -PPLUGIN_ID=<id> -PPLUGIN_MODULE=dsp-plugin-examples `

#### Delete the example plugin:

`$ ./gradlew deletePlugin -PPLUGIN_ID=<id>`

Now the three functions `join_strings`, `map_expand` and `variable_write_log` in the example should be available in the DSP function registry and can be used to create pipelines.

### Running the Unit and Integration Tests

You can run the unit tests from the examples using gradle:

```
$ ./gradlew test
```

You can also run the integration tests:

```
$ ./gradlew integrationTest
```

## SDK API Documentation

All the Splunk jars dependent by this repo have their corresponding `*-javadoc.jar` available too. You can configure your IDE to download the `*-javadoc.jar` to view documentation.

If you use IntelliJ, the IDE will automatically download the `*-javadoc.jar`. You can view the javadoc following https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation

## How will our customers use it?

1. Create a new DSP plugin function by adding it to `dsp-plugin-examples/java/com/splunk/streaming/user/functions`. The template code can be found at `templates/src/main/java/ccom/splunk/streaming/user/functions`.
2. Add your own Java code (make sure to include the package name for it to be accessible `package com.splunk.streaming.user.functions` in this case) to the same folder.
3. Augment the function `getFunctionType` inside the code which would define the function signature.
4. The `call` function would be a wrapper function that would create the objects that would exist inside the customer's code base (in our case, it is the HelloWorld object).
5. Use the commands above to build the plugin, register and upload it to your DSP instance.
6. Test the command using the `eval` block inside a pipeline.
