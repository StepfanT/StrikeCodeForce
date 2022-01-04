import { Link, useNavigate, useParams } from 'react-router-dom';

export default function Register() {
    return (
        <>
            <h2> Register form page</h2>
            
            <Link to={`/`} className="btn btn-success btn-sm">
                Return Home
            </Link>
        </>
    );
};
