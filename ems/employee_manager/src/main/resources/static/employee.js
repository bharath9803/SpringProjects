document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('employee-list');
    const tableBody = document.getElementById('employeesTable').getElementsByTagName('tbody')[0];
    const apiBaseUrl = 'http://localhost:8080/employees';

    // Fetch and display employees
    function fetchEmployees() {
        fetch(apiBaseUrl)
            .then(response => response.json())
            .then(data => {
                tableBody.innerHTML = '';
                data.forEach(employee => {
                    const row = tableBody.insertRow();
                    row.innerHTML = `
                        <td>${employee.id}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.jobTitle}</td>
                        <td>${employee.department}</td>
                        <td>${employee.email}</td>
                        <td>${employee.phoneNumber}</td>
                        <td>${employee.salary}</td>
                        <td>${employee.address}</td>
                        <td>
                            <button onclick="editEmployee(${employee.id})">Edit</button>
                            <button onclick="deleteEmployee(${employee.id})">Delete</button>
                        </td>
                    `;
                });
            });
    }

    // Add or update employee
    form.addEventListener('submit', (event) => {
        event.preventDefault();
        const employee = {
            id: document.getElementById('employeeId').value,
            firstName: document.getElementById('firstName').value,
            lastName: document.getElementById('lastName').value,
            email: document.getElementById('email').value,
            department: document.getElementById('department').value,
            jobTitle: document.getElementById('jobTitle').value,
            salary: parseFloat(document.getElementById('salary').value),
            phoneNumber: document.getElementById('phoneNumber').value,
            address: document.getElementById('address').value
        };

        const method = employee.id ? 'PUT' : 'POST';
        const url = employee.id ? `${apiBaseUrl}/${employee.id}` : apiBaseUrl;

        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(employee)
        })
        .then(response => response.json())
        .then(() => {
            fetchEmployees();
            form.reset();
        });
    });

    // Edit employee
    window.editEmployee = function(id) {
        fetch(`${apiBaseUrl}/${id}`)
            .then(response => response.json())
            .then(employee => {
                document.getElementById('employeeId').value = employee.id;
                document.getElementById('firstName').value = employee.firstName;
                document.getElementById('lastName').value = employee.lastName;
                document.getElementById('email').value = employee.email;
                document.getElementById('department').value = employee.department;
                document.getElementById('jobTitle').value = employee.jobTitle;
                document.getElementById('salary').value = employee.salary;
                document.getElementById('phoneNumber').value = employee.phoneNumber;
                document.getElementById('address').value = employee.address;
            });
    };

    // Delete employee
    window.deleteEmployee = function(id) {
        fetch(`${apiBaseUrl}/${id}`, {
            method: 'DELETE'
        })
        .then(() => {
            fetchEmployees();
        });
    };

    // Initial fetch
    fetchEmployees();
});
