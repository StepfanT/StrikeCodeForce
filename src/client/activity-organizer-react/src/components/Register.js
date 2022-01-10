import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Errors from "./Errors";

export default function Register({ userStatus }) {
    const [user, setUser] = useState([])
    const [errors, setErrors] = useState([]);

    const history = useNavigate();

    const handleInputChange = event => {
        const { name, value } = event.target;
        setUser({ ...user, [name]: value });
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log(user);
        const response = await fetch("http://localhost:8080/authenticate/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(Object.values(user)),
        });

        if (response.status === 200) {
            history("/");
            console.log("Successful Registration!")
        } else if (response.status === 400) {
            const errors = await response.json();
            setErrors(errors);
        } else if (response.status === 403) {
            setErrors(["Registration failed."]);
        } else {
            setErrors(["Unknown error."]);
        }
    };

    return (
        <div>
            <h2>Register New User</h2>

            <form onSubmit={handleSubmit}>
                <div>
                    <label>Username:</label>
                    <input id="username" name="username" type="text"
                        onChange={handleInputChange}
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input
                        id="password" name="password" type="text"
                        onChange={handleInputChange}
                    />
                </div>
                <div>
                    <label>First Name:</label>
                    <input
                        id="firstName" name="firstName" type="text"
                        onChange={handleInputChange}
                    />
                </div>
                <div>
                    <label>Last Name:</label>
                    <input
                        id="lastName" name="lastName" type="text"
                        onChange={handleInputChange}
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input
                        id="email" name="email" type="text"
                        onChange={handleInputChange}
                    />
                </div>
                <div>
                    <label>Location:</label>
                    <input
                        id="location" name="location" type="text"
                        onChange={handleInputChange}
                    />
                </div>
                <div>
                    <button type="submit">Register</button>
                </div>
            </form>
        </div>
    );
};