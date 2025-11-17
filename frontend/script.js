// script.js
// Frontend JavaScript for Employee Directory:
// - submit the create form (POST /api/employees)
// - fetch and display all employees (GET /api/employees)
// - refresh list after successful create

document.addEventListener('DOMContentLoaded', function () {

    // DOM references
    const form = document.getElementById('employeeForm');
    const resultDiv = document.getElementById('result');

    // Create a container element for the employees list
    // If it doesn't exist in HTML, we create and append it below the resultDiv
    let listContainer = document.getElementById('employeeListContainer');
    if (!listContainer) {
        listContainer = document.createElement('div');
        listContainer.id = 'employeeListContainer';
        resultDiv.insertAdjacentElement('afterend', listContainer);
    }

    // Helper: render an array of employees into a simple HTML table
    function renderEmployeeList(employees) {
        if (!Array.isArray(employees) || employees.length === 0) {
            listContainer.innerHTML = '<p>No employees found.</p>';
            return;
        }

        // Build table HTML
        let html = '<table aria-label="Employees" style="width:100%; border-collapse: collapse;">';
        html += '<thead><tr>';
        html += '<th style="text-align:left; padding:8px; border-bottom:1px solid #e5e7eb">ID</th>';
        html += '<th style="text-align:left; padding:8px; border-bottom:1px solid #e5e7eb">First Name</th>';
        html += '<th style="text-align:left; padding:8px; border-bottom:1px solid #e5e7eb">Last Name</th>';
        html += '<th style="text-align:left; padding:8px; border-bottom:1px solid #e5e7eb">Email</th>';
        html += '</tr></thead><tbody>';

        for (const e of employees) {
            html += '<tr>';
            html += `<td style="padding:8px; border-bottom:1px solid #f3f4f6">${e.id ?? ''}</td>`;
            html += `<td style="padding:8px; border-bottom:1px solid #f3f4f6">${escapeHtml(e.firstName ?? '')}</td>`;
            html += `<td style="padding:8px; border-bottom:1px solid #f3f4f6">${escapeHtml(e.lastName ?? '')}</td>`;
            html += `<td style="padding:8px; border-bottom:1px solid #f3f4f6">${escapeHtml(e.email ?? '—')}</td>`;
            html += '</tr>';
        }

        html += '</tbody></table>';
        listContainer.innerHTML = html;
    }

    // Small helper to prevent naive HTML injection when injecting strings
    function escapeHtml(str) {
        return String(str)
            .replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;')
            .replace(/'/g, '&#39;');
    }

    // Fetch the list of employees from backend and render
    async function loadEmployeeList() {
        try {
            const res = await fetch('http://localhost:8080/api/employees');
            if (!res.ok) {
                // If the backend returns error, show message and clear list
                listContainer.innerHTML = `<p class="text-red">Failed to load employees: ${res.status}</p>`;
                return;
            }
            const data = await res.json();
            renderEmployeeList(data);
        } catch (err) {
            listContainer.innerHTML = `<p class="text-red">Unable to load employees: ${escapeHtml(err.message)}</p>`;
            console.error('Error fetching employees:', err);
        }
    }

    // Initial load of employees on page open
    loadEmployeeList();

    // Handle form submit for creating an employee
    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        // Collect and trim values
        const firstName = document.getElementById('firstName').value.trim();
        const lastName = document.getElementById('lastName').value.trim();
        const email = document.getElementById('email').value.trim();

        // Basic client-side validation
        if (!firstName || !lastName) {
            resultDiv.textContent = 'First name and last name are required.';
            resultDiv.className = 'text-red';
            return;
        }

        const payload = {
            firstName: firstName,
            lastName: lastName,
            email: email || null
        };

        try {
            const response = await fetch('http://localhost:8080/api/employees', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                const errText = await response.text();
                resultDiv.textContent = 'Server error: ' + response.status + ' - ' + errText;
                resultDiv.className = 'text-red';
                return;
            }

            const created = await response.json();

            // Show success and reset form
            resultDiv.innerHTML = `<p class="text-green">Employee created (id: ${created.id})</p>`;
            form.reset();

            // Refresh employee list to include the newly created record
            await loadEmployeeList();

        } catch (err) {
            resultDiv.textContent = 'Network error or backend not reachable: ' + err.message;
            resultDiv.className = 'text-red';
            console.error('Fetch error:', err);
        }
    });
});
