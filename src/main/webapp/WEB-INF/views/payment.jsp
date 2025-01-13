<script src="https://checkout.razorpay.com/v1/checkout.js"></script>

<form action="/create-order" method="POST">
    <input type="hidden" name="productId" value="1">
    <input type="number" name="quantity" value="1" min="1">
    <button type="submit">Create Order</button>
</form>

<script>
    var options = {
        key: 'your_api_key', // Razorpay Key
        amount: 50000, // Amount in paise
        currency: "INR",
        name: "Demo Product",
        description: "Order for Demo Product",
        image: "https://example.com/logo.png",
        order_id: "order_id_from_backend", // Dynamically set order ID from backend
        handler: function (response) {
            // On successful payment, send payment ID to backend for capture
            fetch('/capture-payment?paymentId=' + response.razorpay_payment_id + '&amount=50000')
                .then(res => res.json())
                .then(data => {
                    console.log(data);
                });
        }
    };

    var rzp = new Razorpay(options);
    rzp.open();
</script>
