package org.joow.jtodotxt;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a todo (task).
 */
public final class Todo {
    private static final char SPACE_CHARACTER = ' ';

    private static final char PROJECT_IDENTIFIER = '+';

    private static final char CONTEXT_IDENTIFIER = '@';

    private String name;

    private final List<String> projects = new ArrayList<>();

    private final List<String> contexts = new ArrayList<>();

    private Todo(String name) {
        if (name == null) {
            throw new IllegalArgumentException("A todo should have at least a name.");
        }
        this.name = name;
    }

    private String projectsToString() {
        return stringsListToString(projects, PROJECT_IDENTIFIER);
    }

    private String stringsListToString(List<String> strings, char stringIdentifier) {
        final StringBuilder stringBuilder = new StringBuilder();

        for (final String string : strings) {
            stringBuilder.append(SPACE_CHARACTER).append(stringIdentifier).append(string);
        }

        return stringBuilder.toString();
    }

    private String contextsToString() {
        return stringsListToString(contexts, CONTEXT_IDENTIFIER);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(name);
        stringBuilder.append(projectsToString());
        stringBuilder.append(contextsToString());

        return stringBuilder.toString();
    }

    /**
     * Builder for Todo.
     */
    public static final class Builder {
        private final Todo todo;

        /**
         * Constructs a new Todo Builder with the Todo name.
         * @param todoName the name of the Todo to create.
         * @throws IllegalArgumentException if todoName is null.
         */
        public Builder(String todoName) {
            todo = new Todo(todoName);
        }

        public Builder withProject(String project) {
            todo.projects.add(project);
            return this;
        }

        public Builder withContext(String context) {
            todo.contexts.add(context);
            return this;
        }

        public Todo build() {
            return todo;
        }
    }
}
