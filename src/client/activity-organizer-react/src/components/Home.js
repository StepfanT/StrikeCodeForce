import { useNavigate } from 'react-router-dom';
import StyledButton from './Styles/ButtonStyle';

export default function Home() {

  const navigate = useNavigate();

  const handleClick = () => {
    navigate('/');
  }

  return (
    <>
      <div>
        <h1>
          About Us at So Many Activities
        </h1>
        <p >
          Our name may be funny but our Strike Code Force team takes having fun very seriously.</p>
        <p>
          That's why we created this app to help people gather together.
        </p>
      </div>

      <StyledButton onClick={handleClick}>
        Return Home
      </StyledButton>

    </>
  );
};

