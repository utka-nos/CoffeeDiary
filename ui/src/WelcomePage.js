import React from "react";
import Button from 'react-bootstrap/Button'

export default class WelcomePage extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                Main page!
                <Button>Press me!</Button>
            </div>
        );
    }

}