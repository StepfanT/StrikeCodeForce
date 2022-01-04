import { Link, useNavigate, useParams } from 'react-router-dom';


export default function Browse() {
    return (
        <main style={{ padding: "1rem 0" }}>
            <h2>Browse Activities Page</h2>
            <Link to={`/`} className="btn btn-success btn-sm">
                Return Home
            </Link>
        </main>
    );
}
