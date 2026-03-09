import express from 'express';
import {getProfile, updateProfile, deleteUser, listUsers, listMentors } from '../controllers/userController.js';
const router = express.Router();


router.get('/mentors', listMentors);
router.get('/:id',getProfile);
router.put('/:id',  updateProfile);
router.delete('/:id', deleteUser);
router.get('/', listUsers);


export default router;