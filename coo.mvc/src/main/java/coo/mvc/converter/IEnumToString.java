package coo.mvc.converter;

import org.springframework.core.convert.converter.Converter;

import coo.core.model.IEnum;

/**
 * IEnum转换成字符串转换器。
 */
public class IEnumToString implements Converter<IEnum, String> {
  @Override
  public String convert(IEnum source) {
    return source.getValue();
  }
}
