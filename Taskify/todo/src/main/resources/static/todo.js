document.getElementById('todo-form').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevent the default form submission

    const title = document.getElementById('title').value;
    const completed = document.getElementById('completed').checked;

    const todo = {
        title: title,
        completed: completed
    };

    try {
        const response = await fetch('http://localhost:8080/api/todos/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(todo)
        });

        if (response.ok) {
            const result = await response.json();
            console.log('Todo saved:', result);
            fetchTodos(); // Refresh the table after saving
        } else {
            console.error('Error saving todo:', response.statusText);
        }
    } catch (error) {
        console.error('Network error:', error);
    }
});

// Function to fetch and display todos
async function fetchTodos() {
    try {
        const response = await fetch('http://localhost:8080/api/todos');
        const todos = await response.json();

        const todoList = document.getElementById('todo-list');
        todoList.innerHTML = ''; // Clear existing todos

        todos.forEach(todo => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${todo.id}</td>
                <td>${todo.title}</td>
                <td>${todo.completed ? 'Yes' : 'No'}</td>
                <td>
                    <button onclick="editTodo(${todo.id})">Edit</button>
                    <button onclick="deleteTodo(${todo.id})">Delete</button>
                </td>
            `;
            todoList.appendChild(row);
        });
    } catch (error) {
        console.error('Error fetching todos:', error);
    }
}

// Call fetchTodos when the page loads
document.addEventListener('DOMContentLoaded', fetchTodos);

// Function to delete a todo
async function deleteTodo(id) {
    try {
        const response = await fetch(`http://localhost:8080/api/todos/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            console.log('Todo deleted:', id);
            fetchTodos(); // Refresh the table after deleting
        } else {
            console.error('Error deleting todo:', response.statusText);
        }
    } catch (error) {
        console.error('Network error:', error);
    }
}

// Function to edit a todo
function editTodo(id) {
    // Implement edit functionality here
    console.log('Edit todo with ID:', id);
}
