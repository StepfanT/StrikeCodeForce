import React, { useState } from 'react';
import { getData } from "./testData";

import './App.css';

let activityData = getData();

function TestApp() {
    // the value of the search field 
    const [name, setName] = useState('');

    // the search result
    const [foundActivity, setFoundActivity] = useState(activityData);

    const filter = (e) => {
        const keyword = e.target.value;

        if (keyword !== '') {
            const results = activityData.filter((user) => {
                return user.name.toLowerCase().startsWith(keyword.toLowerCase());
                // Use the toLowerCase() method to make it case-insensitive
            });
            setFoundActivity(results);
        } else {
            setFoundActivity(activityData);
            // If the text field is empty, show all users
        }

        setName(keyword);
    };

    return (
        <div className="container">
            <input
                type="search"
                value={name}
                onChange={filter}
                className="input"
                placeholder="Filter"
            />
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Activity</th>
                        <th>Location</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Owner</th>
                    </tr>
                </thead>
            </table>
            <div className="activity-list">
                {foundActivity && foundActivity.length > 0 ? (
                    foundActivity.map((activity) => (
                        <tr key={activity.activityId} className="activity">                           
                            <td>{activity.location}</td>
                            <td>{activity.date}</td>
                            <td>{activity.time}</td>
                            <td>{activity.createBy}</td>                
                        </tr>

                    ))
                ) : (
                    <h1>No results found!</h1>
                )}
            </div>
        </div>
    );
}

export default TestApp;