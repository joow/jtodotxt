package org.joow.jtodotxt;

import org.junit.Test;

import static org.junit.Assert.*;

public final class TodoTest {
    private static final String DEFAULT_TODO_NAME = "Todo";

    private static final String DEFAULT_PROJECT_NAME = "project";

    private static final String DEFAULT_CONTEXT_NAME = "context";

    private Todo.Builder newTodoBuilder() {
        return new Todo.Builder(DEFAULT_TODO_NAME);
    }

    @Test
    public void createSimpleTodo() {
        final Todo todo = newTodoBuilder().build();

        assertEquals(DEFAULT_TODO_NAME, todo.toString());
    }

    @Test
    public void createTodoWithoutName() {
        try {
            new Todo.Builder(null).build();
            fail("It should be impossible to instanciate a todo without a name.");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("A todo should have at least a name."));
        }
    }

    @Test
    public void createTodoWithOneProject() {
        final Todo todo = newTodoBuilder().withProject(DEFAULT_PROJECT_NAME).build();

        assertEquals("Todo +project", todo.toString());
    }

    @Test
    public void createTodoWithMultipleProjects() {
        final Todo todo = newTodoBuilder().withProject("project1").withProject("project2").withProject("project3")
                .build();

        assertEquals("Todo +project1 +project2 +project3", todo.toString());
    }

    @Test
    public void createTodoWithOneContext() {
        final Todo todo = newTodoBuilder().withContext(DEFAULT_CONTEXT_NAME).build();

        assertEquals("Todo @context", todo.toString());
    }

    @Test
    public void createTodoWithProjectAndContext() {
        final Todo todo = newTodoBuilder().withProject(DEFAULT_PROJECT_NAME).withContext(DEFAULT_CONTEXT_NAME).build();

        assertEquals("Todo +project @context", todo.toString());
    }
}
