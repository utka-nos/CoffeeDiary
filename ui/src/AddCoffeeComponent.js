import React from "react";
import {Form} from "react-bootstrap";
import Button from "react-bootstrap/Button";

export default class AddCoffeeComponent extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            title: ""
        }
    }


    render() {
        return (
            <Form className="w-100 mx-auto">
                <Form.Group className="my-1">
                    <Form.Label>Title</Form.Label>
                    <Form.Control placeholder="Geisha" onChange={(event) => {
                        this.setState({
                            title: event.target.value
                        })
                    }}/>
                </Form.Group>
                <Button className="my-1" onClick={() => console.log(this.state.title)}>Add</Button>
            </Form>
        );
    }
}

