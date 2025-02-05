import { Card } from "@/components/ui/card.tsx";
import { Separator } from "@/components/ui/separator.tsx";
import logoImage from "@/assets/images/learnify_logo.png";
import studentImage from "@/assets/images/student_signup.png";
import teacherImage from "@/assets/images/teacher_signup.png";
import { Button } from "@/components/ui/button.tsx";

const cardData = [
  { image: studentImage, alt: "student-img", text: "Student" },
  { image: teacherImage, alt: "teacher-img", text: "Teacher/Lecturer" },
];

export const SignupFormCard = () => {
  return (
    <div
      className={
        "fixed p-2 inset-0 flex justify-center items-center bg-black bg-opacity-50 backdrop-blur-sm"
      }
    >
      <Card
        className={
          "dark:bg-blend-darken p-4 overflow-hidden md:h-[500px] md:w-[700px] lg:w-[800px]  md:flex-row items-center relative"
        }
      >
        <h1 className={"text-lg flex flex-row items-center gap-2"}>
          <img alt={"logo"} src={logoImage} className={"h-8 w-8"} /> Wanna Join
          with us?
        </h1>
        <Separator orientation="horizontal" className={"my-2"} />
        <h2 className={"text-lg text-center my-4"}>Who are you?</h2>
        <div
          className={"w-full flex flex-row items-center gap-2 justify-center"}
        >
          {cardData.map((data, index) => (
            <Card
              key={index}
              className="mx-auto group cursor-pointer hover:border-primary border-2 h-1/2 p-4 max-w-sm shadow-none w-full md:w-1/2 items-center flex flex-col bg-muted/50"
            >
              <img src={data.image} alt={data.alt} className={"w-40 h-40"} />
              <p className={"mb-2"}>{data.text}</p>
            </Card>
          ))}
        </div>
        <div
          className={
            "flex flex-row justify-between absolute bottom-4 left-0 right-0 px-4"
          }
        >
          <Button disabled={true} variant={"outline"} className={""}>
            Previous
          </Button>
          <Button className={""}> Next</Button>
        </div>
      </Card>
    </div>
  );
};
