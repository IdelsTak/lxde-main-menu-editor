/*
 * The MIT License
 *
 * Copyright 2022 Hiram K..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.idelstak.menueditor.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/** @author Hiram K. */
public class Categories implements Iterable<Category> {
  private static final Logger LOG = Logger.getLogger(Categories.class.getName());
  private final Iterable<File> desktopEntryFiles;

  public Categories(Iterable<File> desktopEntryFiles) {
    this.desktopEntryFiles = desktopEntryFiles;
  }

  @Override
  public Iterator<Category> iterator() {
    return StreamSupport.stream(desktopEntryFiles.spliterator(), false)
        .flatMap(
            f -> {
              try (Stream<String> lines = Files.lines(f.toPath())) {
                return lines
                    .filter(s -> s.contains("="))
                    .filter(s -> s.startsWith("Categories"))
                    .map(s -> s.substring(s.indexOf('=') + 1))
                    .map(s -> s.split(";"))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList())
                    .stream();
              } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
              }

              return Stream.empty();
            })
        .takeWhile(s -> s != null && !s.isBlank())
        .collect(Collectors.mapping(Category::new, Collectors.toSet()))
        .iterator();
  }
}
