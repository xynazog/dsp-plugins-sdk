/*
 * Copyright (c) 2019-2020 Splunk, Inc. All rights reserved.
 */

package com.splunk.streaming.user.functions;

import com.splunk.streaming.upl3.core.RuntimeContext;
import com.splunk.streaming.upl3.core.ScalarFunction;
import com.splunk.streaming.upl3.language.Category;
import com.splunk.streaming.upl3.plugins.Categories;
import com.splunk.streaming.upl3.type.CollectionType;
import com.splunk.streaming.upl3.type.FunctionType;
import com.splunk.streaming.upl3.type.StringType;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Scalar function example that joins strings with a provided delimiter.
 *
 * Example SPL usage:
 *
 * ... | eval decryptedstrings = decrypt("encrypted-string");
 */
public class DecryptFunction implements ScalarFunction<String> {

  /* Function name */
  private static final String NAME = "decrypt";

  /* Argument names */
  private static final String STRINGS_ARG = "string";

  /**
   * This method defines the function signature for "decrypt",
   * which has two arguments:
   *
   * string: a string
   *
   * and returns a string.
   *
   * @return FunctionType which defines the function signature
   */
  @Override
  public FunctionType getFunctionType() {
    return FunctionType.newScalarFunctionBuilder(this)
      .returns(StringType.INSTANCE)
      .addArgument(STRINGS_ARG, StringType.INSTANCE)
      .build();
  }

  /**
   * Called at runtime to execute the scalar function.
   * @param context RuntimeContext provides access to arguments
   * @return decrypted string
   */
  @Override
  public String call(RuntimeContext context) {
    // Get first argument by name
    String encrypted_string = context.getArgument(STRINGS_ARG);
    
    // validate arguments, return null if arguments are null
    if (encrypted_string == null) {
      return null;
    }

    // return joined string
    HelloWorld hw = new HelloWorld();
    return(hw.print(encrypted_string));
  }

  // HF -> SC4K (Enterprise Kafka for AmEx - Dedicated VM) - Connector Task using HEC? -> Indexer via HEC  


  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public List<Category> getCategories() {
    // this is a scalar function
    return ImmutableList.of(Categories.SCALAR.getCategory());
  }

  @Override
  public Map<String, Object> getAttributes() {
    return Maps.newHashMap();
  }
}