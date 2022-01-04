import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

export default function View() {
    const [activity, setActivity] = useState([]);

    const getAgents = () => {
        fetch('http://localhost:8080/api/agent')
            .then(response => response.json())
            .then(data => setAgents(data))
            .catch(error => console.log(error));
    };

    useEffect(() => {
        getAgents();
    }, []);
  

    return (
        <div>
            
            <h2 className="my-4">Agents</h2>
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Time</th>
                        <th>Activity</th>
                        <th>Location</th>
                      
                    </tr>
                </thead>
                <tbody>
                    {agents.map(agent => (
                        <tr key={agent.agentId}>
                            <td>{agent.agentId}</td>
                            <td>{agent.firstName}</td>
                            <td>{agent.lastName}</td>
                            <td>
                                <div className="float-right">
                                    <Link to={`/agents/edit/${agent.agentId}`} className="btn btn-primary btn-sm">
                                        <i className="bi bi-pencil"></i> View Details
                                    </Link>                               
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>            
        
            <Link to="/"> Return Home
            </Link>

        </div>
    );
}

