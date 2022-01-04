import { Link, useNavigate, useParams } from 'react-router-dom';


export default function Create() {
    return (
        <main>
            <h2> Create Activity Form</h2>
            <Link to={`/`} className="btn btn-success btn-sm">
                Return Home
            </Link>

        </main>
    );
}
