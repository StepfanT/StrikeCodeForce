import styled from 'styled-components';

const StyledButton = styled.button
  `
  border: 2px solid #4caf50;
  color: green;
  padding: 7px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
  transition: 0.5s all ease-out;

  &:hover {
    background-color: blue;
    color: white;
  }

  `;

const DashboardStyledButton = styled.button
  `
border: 3px solid #4caf50;
color: blue;
padding: 7px 20px;
text-align: center;
text-decoration: none;
display: inline-block;
font-size: 16px;
cursor: pointer;
transition: 0.3s all ease-out;

&:hover {
background-color: palegreen;
color: green;
}

`;

export default StyledButton;


