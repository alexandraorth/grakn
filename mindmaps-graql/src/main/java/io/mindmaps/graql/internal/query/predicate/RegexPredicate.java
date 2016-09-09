/*
 * MindmapsDB - A Distributed Semantic Database
 * Copyright (C) 2016  Mindmaps Research Ltd
 *
 * MindmapsDB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MindmapsDB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MindmapsDB. If not, see <http://www.gnu.org/licenses/gpl.txt>.
 */

package io.mindmaps.graql.internal.query.predicate;

import com.google.common.collect.ImmutableSet;
import io.mindmaps.graql.internal.util.StringConverter;
import org.apache.tinkerpop.gremlin.process.traversal.P;

class RegexPredicate extends AbstractValuePredicate {

    private final String pattern;

    /**
     * @param pattern the regex pattern that this predicate is testing against
     */
    RegexPredicate(String pattern) {
        super(ImmutableSet.of(pattern));
        this.pattern = pattern;
    }

    @Override
    public P<Object> getPredicate() {
        return new P<>((value, p) -> java.util.regex.Pattern.matches((String) p, (String) value), pattern);
    }

    @Override
    public String toString() {
        return "/" + StringConverter.escapeString(pattern) + "/";
    }
}
