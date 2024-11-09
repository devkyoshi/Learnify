import {Button, Card, CardBody, CardFooter, CardHeader, Typography} from "@material-tailwind/react";
import {COLORS} from "@config/colors.js";

export const Home = () => {
    return (
        <div className={'w-full'}>
            <div className={'flex flex-col items-center justify-center pt-20 bg-gray-100 pb-10'}>
                <Typography className={'text-primary'} variant={"h1"}>Free Education </Typography>
                <Typography className={'text-secondary'} variant={"h2"}>&quot;For Every Student From
                    Anywhere&quot;</Typography>
                <Typography className={'mt-2 text-sm'}>
                    We are a non-profit with the mission to provide a free, world-class education for anyone, anywhere.
                </Typography>
                <Button className={'mt-5 rounded-full bg-primary'}>Start learning</Button>
            </div>

            <div className="flex flex-col md:flex-row gap-8 mt-5 p-4 items-center justify-center">
                {/* Student Card */}
               <JoinUsCards
                   button={'Join as a Student'}
                   topic={"Everyone will succeed"}
                   details={`From any starting level, reach new heights with our e-learning platform. Our practical
                    sessions and structured lessons will help you to master the knowledge, and our volunteers will offer
                    support when you need it.`}
                    image={'https://img.freepik.com/premium-photo/photo-3d-illustration-cartoon-teenage-girl-generative-ai_742418-8865.jpg?w=740'}
               />

                {/* Teacher Card */}
                <JoinUsCards
                    button={'Join as a Teacher'}
                    topic={" Everyone will succeed"}
                    details={`From any starting level, reach new heights with our e-learning platform. Our practical
                    sessions and structured lessons will help you to master the knowledge, and our volunteers will offer
                    support when you need it.`}
                    image={'https://img.freepik.com/premium-vector/professional-teacher-woman-3d-cartoon-vector_1338461-2739.jpg?w=740'}
                />

            </div>
        </div>
    )
}


const JoinUsCards = ({image, topic, details, button}) => {
    return(
        <Card className="mt-6 w-96">
            <CardHeader floated={false} shadow={false} color="blue-gray" className="relative h-56">
                <img
                    src={image}
                    alt="card-image"
                />
            </CardHeader>
            <CardBody>
                <Typography variant="h5" color="blue-gray" className="mb-2 text-center">
                    {topic}
                </Typography>
                <Typography className={'text-center'}>
                    &quot; {details} &quot;
                </Typography>
            </CardBody>
            <CardFooter className="pt-0 items-center flex justify-center">
                <Button className={'rounded-full bg-secondary hover:bg-primary'}>{button}</Button>
            </CardFooter>
        </Card>
    )
}


