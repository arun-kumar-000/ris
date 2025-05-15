document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(form);
        const data = Object.fromEntries(formData.entries());

        fetch('/patients/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        .then(resp => resp.json())
        .then(json => {
            if (json.success) {
                const modal = new bootstrap.Modal(document.getElementById('successModal'));
                modal.show();
                form.reset();
            } else {
                alert(json.message || 'Failed to register patient.');
            }
        })
        .catch(err => {
            alert('Server error');
            console.error(err);
        });
    });
});
