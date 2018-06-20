/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.mybatis;


import org.apache.ibatis.session.Configuration;

/**
 * Callback interface that can be customized a {@link Configuration} object generated on auto-configuration.
 *
 * @author Kazuki Shimizu
 * @since 1.2.1
 */
public interface ConfigurationCustomizer {

  /**
   * Customize the given a {@link Configuration} object.
   * @param configuration the configuration object to customize
   */
  void customize(Configuration configuration);

}
