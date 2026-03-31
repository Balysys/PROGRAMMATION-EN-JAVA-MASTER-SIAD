// Sales page scripts
document.addEventListener('DOMContentLoaded', function() {
    // Set today's date as default
    const dateInput = document.getElementById('saleDate');
    if (dateInput) {
        const today = new Date().toISOString().split('T')[0];
        dateInput.value = today;
    }

    // Form validation
    const saleForm = document.querySelector('.sale-form');
    if (saleForm) {
        saleForm.addEventListener('submit', function(e) {
            const quantity = document.getElementById('quantity').value;
            const price = document.getElementById('price').value;

            if (quantity <= 0) {
                alert('Quantity must be greater than 0');
                e.preventDefault();
            }

            if (price <= 0) {
                alert('Price must be greater than 0');
                e.preventDefault();
            }
        });
    }
});
