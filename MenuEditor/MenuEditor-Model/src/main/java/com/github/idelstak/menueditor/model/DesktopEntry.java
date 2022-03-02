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

import java.util.Objects;

/** @author Hiram K. */
public class DesktopEntry {
  private final Category category;
  private final String name;
  private String comment;
  private String command;
  private String workingDir;
  private String icon;
  private boolean startupNotify;
  private boolean runInTerminal;

  public DesktopEntry(Category category, String name) {
    this(category, name, null, null, null, null, false, false);
  }

  private DesktopEntry(
      Category category,
      String name,
      String comment,
      String command,
      String workingDir,
      String icon,
      boolean startupNotify,
      boolean runInTerminal) {
    this.category = category;
    this.name = name;
    this.comment = comment;
    this.command = command;
    this.workingDir = workingDir;
    this.icon = icon;
    this.startupNotify = startupNotify;
    this.runInTerminal = runInTerminal;
  }

  public DesktopEntry withComment(String comment) {
    this.comment = comment;
    return this;
  }

  public DesktopEntry withCommand(String command) {
    this.command = command;
    return this;
  }

  public DesktopEntry withWorkingDir(String workingDir) {
    this.workingDir = workingDir;
    return this;
  }

  public DesktopEntry withIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public DesktopEntry useStartupNotification(boolean startupNotify) {
    this.startupNotify = startupNotify;
    return this;
  }

  public DesktopEntry runsInTerminal(boolean runInTerminal) {
    this.runInTerminal = runInTerminal;
    return this;
  }

  public Category getCategory() {
    return category;
  }

  public String getName() {
    return name;
  }

  public String getComment() {
    return comment;
  }

  public String getCommand() {
    return command;
  }

  public String getWorkingDir() {
    return workingDir;
  }

  public String getIcon() {
    return icon;
  }

  public boolean isStartupNotify() {
    return startupNotify;
  }

  public boolean isRunInTerminal() {
    return runInTerminal;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 71 * hash + Objects.hashCode(this.category);
    hash = 71 * hash + Objects.hashCode(this.name);
    hash = 71 * hash + Objects.hashCode(this.comment);
    hash = 71 * hash + Objects.hashCode(this.command);
    hash = 71 * hash + Objects.hashCode(this.workingDir);
    hash = 71 * hash + Objects.hashCode(this.icon);
    hash = 71 * hash + (this.startupNotify ? 1 : 0);
    hash = 71 * hash + (this.runInTerminal ? 1 : 0);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final DesktopEntry other = (DesktopEntry) obj;
    if (this.startupNotify != other.startupNotify) {
      return false;
    }
    if (this.runInTerminal != other.runInTerminal) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(this.comment, other.comment)) {
      return false;
    }
    if (!Objects.equals(this.command, other.command)) {
      return false;
    }
    if (!Objects.equals(this.workingDir, other.workingDir)) {
      return false;
    }
    if (!Objects.equals(this.icon, other.icon)) {
      return false;
    }
    return Objects.equals(this.category, other.category);
  }
}
