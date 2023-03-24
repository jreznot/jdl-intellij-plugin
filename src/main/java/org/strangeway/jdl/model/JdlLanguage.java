/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.strangeway.jdl.model;

public enum JdlLanguage implements JdlModelEnum {
  ALBANIAN("al"),
  ARABIC("ar-ly"),
  ARMENIAN("hy"),
  BELARUSIAN("by"),
  BENGALI("bn"),
  BULGARIAN("bg"),
  CATALAN("ca"),
  CHINESE("zh-tw"),
  CHINESE_SIMPLIFIED("zh-cn"),
  CROATIAN("hr"),
  CZECH("cs"),
  DANISH("da"),
  DUTCH("nl"),
  ENGLISH("en"),
  ESTONIAN("et"),
  FARSI("fa"),
  FINNISH("fi"),
  FRENCH("fr"),
  GALICIAN("gl"),
  GERMAN("de"),
  GREEK("el"),
  HINDI("hi"),
  HUNGARIAN("hu"),
  INDONESIAN("id"),
  ITALIAN("it"),
  JAPANESE("ja"),
  KOREAN("ko"),
  MARATHI("mr"),
  MYANMAR("my"),
  POLISH("po"),
  PORTUGUESE_BRAZILIAN("pt-br"),
  PORTUGUESE("pt-br"),
  PUNJABI("pa"),
  ROMANIAN("ro"),
  RUSSIAN("ru"),
  SLOVAK("sk"),
  SERBIAN("sr"),
  SINHALA("si"),
  SPANISH("es"),
  SWEDISH("sv"),
  TURKISH("tr"),
  TAMIL("ta"),
  TELUGU("te"),
  THAI("th"),
  UKRAINIAN("uk"),
  UZBEK_CYR("uz-Cyrl-uz"),
  UZBEK_LAT("uz-Latn-uz"),
  VIETNAMESE("vi");

  private final String id;

  JdlLanguage(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
