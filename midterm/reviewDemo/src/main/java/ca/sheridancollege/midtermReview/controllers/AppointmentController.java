package ca.sheridancollege.midtermReview.controllers;

import ca.sheridancollege.midtermReview.beans.Appointment;
import ca.sheridancollege.midtermReview.database.DatabaseAccess;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    private final DatabaseAccess da;

    public AppointmentController(DatabaseAccess da) {
        this.da = da;
    }

    @GetMapping("/")
    public String index(@NotNull Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("appointmentList", da.getAllAppointments());
        return "index";
    }

    @PostMapping("/insertAppointment")
    public String insertAppointment(Model model, @ModelAttribute Appointment appointment) {
        List<Appointment> existingAppointments = da.getAppointmentById(appointment.getId());

        if (existingAppointments.isEmpty()) {
            da.insertAppointment(appointment);
        } else {
            da.updateAppointment(appointment);
        }
model.addAttribute("appointment", new Appointment());
        return "redirect:/";
    }
    @GetMapping ("/editAppointment/{id}")
    public String editAppointment (Model model, @PathVariable Long id) {
            List<Appointment> appointmentList = da.getAppointmentById(id);
            if (!appointmentList.isEmpty()) {
                model.addAttribute("appointment", appointmentList.get(0));
            } else {
                model.addAttribute("appointment", new Appointment());
            }
            model.addAttribute("appointmentList", da.getAllAppointments());
            return "index";
        }
    @GetMapping("/deleteAppointment/{id}")
    public String deleteAppointment(Model model, @PathVariable Long id) {
        da.deleteAppointment(id);
        return "redirect:/";
    }
}
