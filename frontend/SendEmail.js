document.getElementById("appointmentForm").addEventListener("submit", async function (e) {

    e.preventDefault();

    const form = this;

    const formData = new FormData(form);

    const data = {
        fullName: formData.get("fullName"),
        email: formData.get("email"),
        phoneNumber: formData.get("phoneNumber"),
        serviceType: formData.get("serviceType"),
        date: formData.get("date"),
        additionalMessage: formData.get("additionalMessage")
    };

    console.log("Sending appointment:", data);

    try {

        const response = await fetch("http://localhost:8080/api/appointments", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {

            const result = await response.json();

            console.log("Server response:", result);

            alert("Appointment request sent successfully!");

            form.reset();

        } else {

            const errorText = await response.text();

            console.error("Server error:", errorText);

            alert("Failed to send appointment request. Please try again.");
        }

    } catch (error) {

        console.error("Connection error:", error);

        alert(
            "Could not connect to the appointment server. " +
            "Please make sure the Spring Boot application is running."
        );
    }
});