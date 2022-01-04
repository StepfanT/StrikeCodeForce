import { Link, useNavigate, useParams } from 'react-router-dom';

export default function Points() {
    return (
        <>
            <h2> You have zero points.</h2>
            <Link to={`/`} className="btn btn-success btn-sm">
                Return Home
            </Link>
        </>
    );
}

