import "./../style/RecruiterForm.css";
import { saveRecruiterData } from "../api/recruiter";
import { useMutation, useQuery } from "@tanstack/react-query";
import { useEffect } from "react";

export default function RecruiterForm({
  recruiter,
  updateRecruiter,
  updateRecDataSaved,
}) {
  const mutation = useMutation({
    mutationFn: saveRecruiterData,
    onSuccess: (data) => {
      updateRecruiter("id", data);
    },
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    mutation.mutate({ name: recruiter.name, company: recruiter.company });
    updateRecDataSaved(true);
  };

  return (
    <>
      <form className="recruiter_data" onSubmit={handleSubmit}>
        <p id="title">PROVIDE YOUR RECRUITER DETAILS</p>
        <input
          id="ip_name"
          value={recruiter.name}
          onChange={(e) => updateRecruiter("name", e.target.value)}
          placeholder="Your Name"
        ></input>
        <input
          id="ip_company"
          value={recruiter.company}
          onChange={(e) => updateRecruiter("company", e.target.value)}
          placeholder="Your Company"
        ></input>
        <button id="btn_submit" type="submit">
          Start Game
        </button>
      </form>
    </>
  );
}
