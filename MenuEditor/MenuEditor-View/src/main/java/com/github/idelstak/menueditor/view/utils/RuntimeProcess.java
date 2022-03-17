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

package com.github.idelstak.menueditor.view.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Executes a string command using a separate process.
 *
 * <p>Then, it reads the output(s) from the execution and returns it.
 *
 * @author Hiram K.
 */
public class RuntimeProcess {

  private final String command;

  public RuntimeProcess(String command) {
    this.command = Objects.requireNonNull(command, "Command should not be null");
  }

  public String execute() throws IOException {
    String output = "";

    // Execute the command in a separate process
    var process = Runtime.getRuntime().exec(command);

    // Get the input stream connected to the normal output of the process
    try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
      // Read all lines while delimiting them using a line separator
      String line;
      while ((line = reader.readLine()) != null) {
        output += line + System.lineSeparator();
      }
    }

    return output;
  }
}
