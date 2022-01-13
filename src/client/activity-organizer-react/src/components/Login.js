import React, { useState, useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import jwtDecode from "jwt-decode";
import Errors from "./Errors";
import AuthContext from "../context/AuthContext";

export default function Login() {
    const [user, setUser] = useState({})
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState([]);
    const [_, setUserStatus] = useContext(AuthContext);

    const history = useNavigate();

    const handleInputChange = event => {
        const { name, value } = event.target;
        setUser({ ...user, [name]: value });
    }


    const handleSubmit = async (event) => {
        event.preventDefault();

        const response = await fetch("http://localhost:8080/authenticate", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username,
                password,
            }),
        });

        // This code executes if the request is successful
        if (response.status === 200) {
            const { jwt_token } = await response.json();

            console.log(jwtDecode(jwt_token));
            console.log(jwt_token);
            //userStatus.login(username)
            //userStatus.login(jwtDecode(jwt_token));
            setUserStatus({ user: jwtDecode(jwt_token) });
            localStorage.setItem("token", jwt_token);
            history("/dashboard");
            console.log("Successful Login!")
        } else if (response.status === 400) {
            const errors = await response.json();
            setErrors(errors);
        } else if (response.status === 403) {
            console.log(username);
            console.log(password);
            setErrors(["Login failed."]);
        } else {
            setErrors(["Unknown error."]);
        }
    };

    return (
        <div>
            <h2>Login</h2>

            <Errors errors={errors} />

            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="username">Username:</label>
                    <input
                        type="text"
                        onChange={(event) => setUsername(event.target.value)}
                        id="username"
                    />
                </div>
                <div>
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        onChange={(event) => setPassword(event.target.value)}
                        id="password"
                    />
                </div>
                <div>
                    <button type="submit">Login</button>
                </div>
            </form>
        </div>
    );
};

/*
P@ssw0rd!
           {errors.map((error, i) => (
                <Errors key={i} msg={error} />
            ))}

*/