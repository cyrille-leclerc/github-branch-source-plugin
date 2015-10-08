/*
 * The MIT License
 *
 * Copyright 2015 CloudBees, Inc.
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

package org.jenkinsci.plugins.github_branch_source;

import hudson.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Stephen Connolly
 */
public abstract class RepositoryUriResolver {

    public abstract String getRepositoryUri(String apiUri, String owner, String repository);

    public static String hostnameFromApiUri(String apiUri) {
        apiUri = Util.fixEmptyAndTrim(apiUri);
        if (apiUri == null) {
            return "github.com";
        }
        Pattern enterprise = Pattern.compile("^https?://(([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*"
                + "([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])/api/v3/?.*$");
        Matcher matcher = enterprise.matcher(apiUri);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        // fall back to github
        return "github.com";
    }

}